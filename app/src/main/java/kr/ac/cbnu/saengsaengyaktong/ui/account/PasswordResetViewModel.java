package kr.ac.cbnu.saengsaengyaktong.ui.account;

import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetViewModel extends ViewModel {
    private static final String EMAIL_KEY = "email";

    private final SavedStateHandle handle;

    public PasswordResetViewModel(SavedStateHandle state) {
        this.handle = state;
    }

    public MutableLiveData<String> getEmail() {
        return handle.getLiveData(EMAIL_KEY);
    }

    public void setEmail(String value) {
        handle.set(EMAIL_KEY, value);
    }

    public Task<Void> resetPassword() {
        final String email = getEmail().getValue();
        return FirebaseAuth.getInstance().sendPasswordResetEmail(email);
    }

    public static final ViewModelInitializer<PasswordResetViewModel> initializer = new ViewModelInitializer<>(
            PasswordResetViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                assert app != null;
                SavedStateHandle savedStateHandle = createSavedStateHandle(creationExtras);

                return new PasswordResetViewModel(savedStateHandle);
            }
    );
}