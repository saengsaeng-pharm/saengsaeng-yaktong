package kr.ac.cbnu.saengsaengyaktong.domain.entity.repository;

import com.google.android.gms.tasks.Task;
import com.google.common.collect.Lists;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicinePlan;

public class MedicinePlansRepository {
    private static final String NAME = "medicine_plans";
    private static final String USER_ID_FIELD = "user_id";

    private static MedicinePlansRepository instance;

    private final CollectionReference collection;

    private MedicinePlansRepository() {
        collection = FirebaseFirestore.getInstance().collection(NAME);
    }

    public static MedicinePlansRepository getInstance() {
        if (instance == null) {
            instance = new MedicinePlansRepository();
        }

        return instance;
    }

    public Task<DocumentReference> add(String medicineId, String memo, @Nullable String imageUrl, @Nullable Integer breakfastDelay, @Nullable Integer lunchDelay, @Nullable Integer dinnerDelay) {
        final String userId = FirebaseAuth.getInstance().getUid();

        final Map<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        data.put("medicine_id", medicineId);
        data.put("memo", memo);
        if (imageUrl != null) data.put("image_url", imageUrl);
        if (breakfastDelay != null) data.put("breakfast_delay", breakfastDelay);
        if (lunchDelay != null) data.put("lunch_delay", lunchDelay);
        if (dinnerDelay != null) data.put("dinner_delay", dinnerDelay);

        return collection.add(data);
    }

    public List<MedicinePlan> get() {
        final String userId = FirebaseAuth.getInstance().getUid();
        final QuerySnapshot docs = collection.whereEqualTo(USER_ID_FIELD, userId).get().getResult();

        return Lists.transform(docs.getDocuments(), doc -> doc.toObject(MedicinePlan.class).withId(doc.getId()));
    }

    public Task<Void> delete(String id) {
        final String userId = FirebaseAuth.getInstance().getUid();
        return collection.document(id).delete();
    }
}
