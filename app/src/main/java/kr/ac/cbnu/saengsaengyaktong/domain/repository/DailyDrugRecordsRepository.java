package kr.ac.cbnu.saengsaengyaktong.domain.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.DailyDrugRecord;
import kr.ac.cbnu.saengsaengyaktong.utils.DateUtils;

public class DailyDrugRecordsRepository {
    private static final String NAME = "daily_drug_records";

    private static DailyDrugRecordsRepository instance;

    private final CollectionReference collection;

    private DailyDrugRecordsRepository() {
        collection = FirebaseFirestore.getInstance().collection(NAME);
    }

    public static DailyDrugRecordsRepository getInstance() {
        if (instance == null) {
            instance = new DailyDrugRecordsRepository();
        }

        return instance;
    }

    public Single<List<DailyDrugRecord>> get(Date date) {
        final String userId = FirebaseAuth.getInstance().getUid();
        final Date today = DateUtils.getTodayDate();

        return Single.create(emitter -> {
            collection
                    .whereEqualTo("date", new Timestamp(today))
                    .whereEqualTo("user_id", userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        final List<DocumentSnapshot> docs = task.getResult().getDocuments();
                        final List<DailyDrugRecord> items = docs.stream().map(doc -> doc.toObject(DailyDrugRecord.class).withId(doc.getId())).collect(Collectors.toList());

                        emitter.onSuccess(items);
                    });
        });
    }

    public void set(DailyDrugRecord data) {
        if (data.getId() == null) {
            collection.add(data).addOnCompleteListener(task -> {
                if (!task.isSuccessful()) return;
                final String id = task.getResult().getId();
                data.withId(id);
            });
        } else {
            collection.document(data.getId()).set(data);
        }
    }

    public void delete(String id) {
        final String userId = FirebaseAuth.getInstance().getUid();
        collection.document(id).delete();
    }
}
