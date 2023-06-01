package kr.ac.cbnu.saengsaengyaktong.domain.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.GenderType;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.UserProfile;

public class UserProfilesRepository {
    private static final String NAME = "user_profiles";
    private static final String USER_ID_FIELD = "user_id";

    private static UserProfilesRepository instance;

    private final CollectionReference collection;

    private UserProfilesRepository() {
        collection = FirebaseFirestore.getInstance().collection(NAME);
    }

    public static UserProfilesRepository getInstance() {
        if (instance == null) {
            instance = new UserProfilesRepository();
        }

        return instance;
    }

    public Task<Void> add(String name, Date birthDate, GenderType genderType) {
        final String userId = FirebaseAuth.getInstance().getUid();

        final Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("birth_date", new Timestamp(birthDate));
        data.put("gender", genderType.name().toLowerCase());

        return collection.document(userId).set(data);
    }

    public UserProfile get() {
        final String userId = FirebaseAuth.getInstance().getUid();
        return collection.document(userId).get().getResult().toObject(UserProfile.class);
    }
}
