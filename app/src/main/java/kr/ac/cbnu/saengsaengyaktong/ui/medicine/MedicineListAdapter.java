package kr.ac.cbnu.saengsaengyaktong.ui.medicine;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.databinding.MedicineCheckItemBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.MedicineViewModel;

public class MedicineListAdapter extends ListAdapter<MedicineViewModel, MedicineListAdapter.ViewHolder> {
    private static final String TAG = "MedicineListAdapter";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MedicineCheckItemBinding binding;
        private MedicineViewModel viewModel;

        public ViewHolder(MedicineCheckItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setViewModel(MedicineViewModel viewModel) {
            this.viewModel = viewModel;
            binding.setViewModel(viewModel);
        }
    }

    public interface OnClickListener {
        void onClick(MedicineViewModel item);
    }

    public MedicineListAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnClickListener onClickListener;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final MedicineViewModel viewModel = getItem(position);
        viewHolder.setViewModel(viewModel);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final MedicineCheckItemBinding binding = MedicineCheckItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(viewHolder.viewModel);
            }
        });

        return viewHolder;
    }

    public List<? extends MedicineViewModel> getItems() {
        return getCurrentList();
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public static final DiffUtil.ItemCallback<MedicineViewModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MedicineViewModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull MedicineViewModel oldViewModel, @NonNull MedicineViewModel newViewModel) {
                    return oldViewModel.getId().equals(newViewModel.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull MedicineViewModel oldViewModel, @NonNull MedicineViewModel newViewModel) {
                    return oldViewModel.getId().equals(newViewModel.getId()); // should be fixed
                }
            };
}
