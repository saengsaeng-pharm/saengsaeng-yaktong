package kr.ac.cbnu.saengsaengyaktong.ui.account;

import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.GenderType;
import kr.ac.cbnu.saengsaengyaktong.domain.repository.UserProfilesRepository;

public class SignUpViewModel extends ViewModel {
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String PASSWORD_CONFIRM_KEY = "password_confirm";
    private static final String NAME_KEY = "name";
    private static final String BIRTH_DATE_KEY = "birth_date";
    private static final String GENDER_KEY = "gender";
    private static final String AGREEMENT_KEY = "agreement";

    private final SavedStateHandle handle;
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public SignUpViewModel(SavedStateHandle state) {
        this.handle = state;
        setGender(GenderType.MALE);
    }

    public boolean isEmailValid() {
        System.out.println("isEmailValid()");
        final String email = getEmail().getValue();
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid() {
        System.out.println("isPasswordValid()");
        final String password = getPassword().getValue();
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    public boolean isPasswordConfirmValid() {
        System.out.println("isPasswordConfirmValid()");
        final String passwordConfirm = getPasswordConfirm().getValue();
        return !TextUtils.isEmpty(passwordConfirm) && passwordConfirm.equals(getPassword().getValue());
    }

    public MutableLiveData<String> getEmail() {
        return handle.getLiveData(EMAIL_KEY);
    }

    public MutableLiveData<String> getPassword() {
        return handle.getLiveData(PASSWORD_KEY);
    }

    public MutableLiveData<String> getPasswordConfirm() {
        return handle.getLiveData(PASSWORD_CONFIRM_KEY);
    }

    public MutableLiveData<String> getName() {
        return handle.getLiveData(NAME_KEY);
    }

    public LiveData<Date> getBirthDate() {
        return handle.getLiveData(BIRTH_DATE_KEY);
    }

    public LiveData<GenderType> getGender() {
        return handle.getLiveData(GENDER_KEY);
    }

    public MutableLiveData<Boolean> getAgreement() {
        return handle.getLiveData(AGREEMENT_KEY);
    }

    public void setEmail(String value) {
        handle.set(EMAIL_KEY, value);
    }

    public void setPassword(String value) {
        handle.set(PASSWORD_KEY, value);
    }

    public void setPasswordConfirm(String value) {
        handle.set(PASSWORD_CONFIRM_KEY, value);
    }

    public void setName(String value) {
        handle.set(NAME_KEY, value);
    }

    public void setBirthDate(Date value) {
        handle.set(BIRTH_DATE_KEY, value);
    }

    public void setGender(GenderType value) {
        handle.set(GENDER_KEY, value);
    }

    public void setAgreement(Boolean value) {
        handle.set(AGREEMENT_KEY, value);
    }

    public Task<AuthResult> signUp() {
        final String email = getEmail().getValue();
        final String password = getPassword().getValue();

        return auth.createUserWithEmailAndPassword(email, password);
    }

    public Task<Void> registerProfile() {
        final UserProfilesRepository repository = UserProfilesRepository.getInstance();

        final String name = getName().getValue();
        final Date birthDate = getBirthDate().getValue();
        final GenderType gender = getGender().getValue();

        return repository.add(name, birthDate, gender);
    }

    public static final ViewModelInitializer<SignUpViewModel> initializer = new ViewModelInitializer<>(
            SignUpViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                assert app != null;
                SavedStateHandle savedStateHandle = createSavedStateHandle(creationExtras);

                return new SignUpViewModel(savedStateHandle);
            }
    );
}