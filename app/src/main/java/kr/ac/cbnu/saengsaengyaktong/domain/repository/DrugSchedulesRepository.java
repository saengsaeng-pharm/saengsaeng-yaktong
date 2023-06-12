package kr.ac.cbnu.saengsaengyaktong.domain.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.DrugSchedule;

public class DrugSchedulesRepository {
    private static final String NAME = "drug_schedules";

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

    public Single<List<DrugSchedule>> get() {
        return Single.create(emitter -> {
            final String userId = FirebaseAuth.getInstance().getUid();
            collection
                    .whereEqualTo("user_id", userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        final List<DocumentSnapshot> docs = task.getResult().getDocuments();
                        final List<DrugSchedule> items = docs.stream().map(doc -> doc.toObject(DrugSchedule.class).withId(doc.getId())).collect(Collectors.toList());

                        emitter.onSuccess(items);
                    });
        });
    }

    public Completable set(DrugSchedule data) {
        return Completable.create(emitter -> {
            if (data.getId() == null) {
                collection.add(data).addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) return;
                    final String id = task.getResult().getId();
                    data.withId(id);

                    emitter.onComplete();
                });
            } else {
                collection.document(data.getId()).set(data).addOnCompleteListener(task -> emitter.onComplete());
            }
        });
    }

    public Task<Void> delete(String id) {
        final String userId = FirebaseAuth.getInstance().getUid();
        return collection.document(id).delete();
    }
}
