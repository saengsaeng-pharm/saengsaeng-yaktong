package kr.ac.cbnu.saengsaengyaktong.ui.schedules;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.databinding.DrugScheduleItemBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.drugs.DailyDrugRecordViewModel;

public class DrugSchedulesAdapter extends ListAdapter<DailyDrugRecordViewModel, DrugSchedulesAdapter.ViewHolder> {
    private static final String TAG = "DrugSchedulesAdapter";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final DrugScheduleItemBinding binding;
        private DailyDrugRecordViewModel viewModel;

        public ViewHolder(DrugScheduleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setViewModel(DailyDrugRecordViewModel viewModel) {
            this.viewModel = viewModel;
            binding.setViewModel(viewModel);
        }
    }

    public interface OnClickListener {
        void onClick(DailyDrugRecordViewModel item);
    }

    public DrugSchedulesAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnClickListener onClickListener;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final DailyDrugRecordViewModel viewModel = getItem(position);
        viewHolder.setViewModel(viewModel);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final DrugScheduleItemBinding binding = DrugScheduleItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(viewHolder.viewModel);
            }
        });

        return viewHolder;
    }

    public List<? extends DailyDrugRecordViewModel> getItems() {
        return getCurrentList();
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public static final DiffUtil.ItemCallback<DailyDrugRecordViewModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DailyDrugRecordViewModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull DailyDrugRecordViewModel oldViewModel, @NonNull DailyDrugRecordViewModel newViewModel) {
                    return oldViewModel.getId().equals(newViewModel.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull DailyDrugRecordViewModel oldViewModel, @NonNull DailyDrugRecordViewModel newViewModel) {
                    return oldViewModel.getId().equals(newViewModel.getId()); // should be fixed
                }
            };
}
