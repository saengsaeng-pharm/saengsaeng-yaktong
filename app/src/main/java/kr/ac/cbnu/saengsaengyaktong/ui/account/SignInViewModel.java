package kr.ac.cbnu.saengsaengyaktong.ui.account;

import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignInViewModel extends ViewModel {
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private final SavedStateHandle handle;

    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public SignInViewModel(SavedStateHandle state) {
        this.handle = state;
    }

    public MutableLiveData<String> getEmail() {
        return handle.getLiveData(EMAIL_KEY);
    }

    public MutableLiveData<String> getPassword() {
        return handle.getLiveData(PASSWORD_KEY);
    }

    public void setEmail(String value) {
        handle.set(EMAIL_KEY, value);
    }

    public void setPassword(String value) {
        handle.set(PASSWORD_KEY, value);
    }

    public Task<AuthResult> signIn() {
        final String email = getEmail().getValue();
        final String password = getPassword().getValue();

        return auth.signInWithEmailAndPassword(email, password);
    }

    public static final ViewModelInitializer<SignInViewModel> initializer = new ViewModelInitializer<>(
            SignInViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                assert app != null;
                SavedStateHandle savedStateHandle = createSavedStateHandle(creationExtras);

                return new SignInViewModel(savedStateHandle);
            }
    );
}