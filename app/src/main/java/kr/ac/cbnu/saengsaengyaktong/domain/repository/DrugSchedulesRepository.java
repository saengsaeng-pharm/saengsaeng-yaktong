package kr.ac.cbnu.saengsaengyaktong.domain.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.TimeOfDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicinePlan;

public class DrugSchedulesRepository {
    private static final String NAME = "drug_schedules";
    private static final String USER_ID_FIELD = "user_id";

    private static DrugSchedulesRepository instance;

    private final CollectionReference collection;

    private DrugSchedulesRepository() {
        collection = FirebaseFirestore.getInstance().collection(NAME);
    }

    public static DrugSchedulesRepository getInstance() {
        if (instance == null) {
            instance = new DrugSchedulesRepository();
        }

        return instance;
    }

    public Task<DocumentReference> add(String medicineId, String memo, @Nullable String imageUrl, @Nullable TimeOfDay breakfastTime, @Nullable TimeOfDay lunchTime, @Nullable TimeOfDay dinnerTime) {
        final String userId = FirebaseAuth.getInstance().getUid();

        final Map<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        data.put("drug_id", medicineId);
        data.put("memo", memo);
        if (imageUrl != null) data.put("image_url", imageUrl);
        if (breakfastTime != null) data.put("breakfast_time", breakfastTime);
        if (lunchTime != null) data.put("lunch_time", lunchTime);
        if (dinnerTime != null) data.put("dinner_Time", dinnerTime);

        return collection.add(data);
    }

    public List<MedicinePlan> get() {
        final String userId = FirebaseAuth.getInstance().getUid();
        final QuerySnapshot docs = collection.whereEqualTo(USER_ID_FIELD, userId).get().getResult();

        return docs.getDocuments().stream().map(doc -> doc.toObject(MedicinePlan.class).withId(doc.getId())).collect(Collectors.toList());
    }

    public Task<Void> delete(String id) {
        final String userId = FirebaseAuth.getInstance().getUid();
        return collection.document(id).delete();
    }
}
