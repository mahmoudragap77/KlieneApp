package com.example.klieneapp.viewmodels

import kotlinx.coroutines.channels.Channel
import androidx.lifecycle.ViewModel
import com.example.klieneapp.data.User
import com.example.klieneapp.util.RegisterFieldState
import com.example.klieneapp.util.RegisterValidation
import com.example.klieneapp.util.Resource
import com.example.klieneapp.util.validateEmail
import com.example.klieneapp.util.validatePassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
        private val firebaseAuth: FirebaseAuth,
    private val dbFireStore: FirebaseFirestore
) : ViewModel() {
    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register : Flow<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()

            fun registerWithEmailAndPassword(user: User, password: String) {

                if (checkValidation(user, password)) {

                    runBlocking {
                        _register.emit(Resource.Loading())
                    }
                    firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                        .addOnSuccessListener {
                            it.user?.let {
                                saveUserInfo(it.uid, user)
                            }
                        }
                        .addOnFailureListener {
                            _register.value = Resource.Error(it.message.toString())
                        }


                }
                else{
                    val registerFieldState = RegisterFieldState(
                        validateEmail(user.email), validatePassword(password)
                    )
                    runBlocking {
                        _validation.send(registerFieldState)
                    }
                }
            }

    private fun checkValidation(user: User, password: String) : Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = emailValidation is RegisterValidation.successValidations
                && passwordValidation is RegisterValidation.successValidations
        return shouldRegister
    }
    private fun RegisterViewModel.saveUserInfo(userUid: String, user:User) {
        dbFireStore.collection("user").
        document(userUid)
            .set(user)
            .addOnSuccessListener {
                _register.value = Resource.Success(user)
            }.addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())
            }
    }

}

