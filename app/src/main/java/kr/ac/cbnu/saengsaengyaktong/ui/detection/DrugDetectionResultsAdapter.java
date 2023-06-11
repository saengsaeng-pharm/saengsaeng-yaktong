package kr.ac.cbnu.saengsaengyaktong.ui.detection;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.databinding.DrugDetectionResultItemBinding;

public class DrugDetectionResultsAdapter extends ListAdapter<DrugDetectionResult, DrugDetectionResultsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final DrugDetectionResultItemBinding binding;
        private DrugDetectionResult item;

        public ViewHolder(DrugDetectionResultItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setItem(DrugDetectionResult item) {
            this.item = item;
            binding.setItem(item);
        }
    }

    public interface OnClickListener {
        void onClick(DrugDetectionResult item);
    }

    private List<DrugDetectionResult> items = new ArrayList<>();
    private OnClickListener onClickListener;

    public DrugDetectionResultsAdapter() {
        super(DIFF_CALLBACK);
    }

    public void addItem(DrugDetectionResult item) {
        items.add(item);
        items.sort((a, b) -> Float.compare(b.getProbability(), a.getProbability()));
        submitList(items);
    }

    public void setItems(List<DrugDetectionResult> items) {
        this.items = items;
        submitList(items);
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final DrugDetectionResult item = getItem(position);
        viewHolder.setItem(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final DrugDetectionResultItemBinding binding = DrugDetectionResultItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(viewHolder.item);
            }
        });

        return viewHolder;
    }

    public static final DiffUtil.ItemCallback<DrugDetectionResult> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DrugDetectionResult>() {
                @Override
                public boolean areItemsTheSame(@NonNull DrugDetectionResult oldItem, @NonNull DrugDetectionResult newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull DrugDetectionResult oldItem, @NonNull DrugDetectionResult newItem) {
                    return oldItem.getId().equals(newItem.getId()); // should be fixed
                }
            };
}
