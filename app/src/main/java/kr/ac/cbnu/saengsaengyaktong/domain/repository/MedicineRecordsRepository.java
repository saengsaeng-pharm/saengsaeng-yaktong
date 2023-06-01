package kr.ac.cbnu.saengsaengyaktong.domain.entity.repository;

import com.google.android.gms.tasks.Task;
import com.google.common.collect.Lists;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicineRecord;

public class MedicineRecordsRepository {
    private static final String NAME = "medicine_records";
    private static final String USER_ID_FIELD = "user_id";

    private static MedicineRecordsRepository instance;

    private final CollectionReference collection;

    private MedicineRecordsRepository() {
        collection = FirebaseFirestore.getInstance().collection(NAME);
    }

    public static MedicineRecordsRepository getInstance() {
        if (instance == null) {
            instance = new MedicineRecordsRepository();
        }

        return instance;
    }

    public Task<DocumentReference> add(String medicineId, Date date, Date breakfast, Date lunch, Date dinner) {
        final String userId = FirebaseAuth.getInstance().getUid();

        final Map<String, Object> data = new HashMap<>();
        data.put("user_id", userId);
        data.put("medicine_id", medicineId);
        data.put("date", date);
        if (breakfast != null) data.put("breakfast", breakfast);
        if (lunch != null) data.put("lunch", lunch);
        if (dinner != null) data.put("dinner", dinner);

        return collection.add(data);
    }

    public List<MedicineRecord> get() {
        final String userId = FirebaseAuth.getInstance().getUid();
        final QuerySnapshot docs = collection.whereEqualTo(USER_ID_FIELD, userId).get().getResult();

        return Lists.transform(docs.getDocuments(), doc -> doc.toObject(MedicineRecord.class).withId(doc.getId()));
    }

    public Task<Void> delete(String id) {
        final String userId = FirebaseAuth.getInstance().getUid();
        return collection.document(id).delete();
    }
}
