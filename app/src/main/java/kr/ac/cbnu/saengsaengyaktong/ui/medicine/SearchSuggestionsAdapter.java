package kr.ac.cbnu.saengsaengyaktong.ui.medicine;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.api.DrugItem;
import kr.ac.cbnu.saengsaengyaktong.databinding.DrugSearchSuggestionItemBinding;

public class SearchSuggestionsAdapter extends ListAdapter<DrugItem, SearchSuggestionsAdapter.ViewHolder> {
    private static final String TAG = "SearchSuggestionsAdapter";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final DrugSearchSuggestionItemBinding binding;
        private DrugItem item;

        public ViewHolder(DrugSearchSuggestionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setViewModel(DrugItem item) {
            this.item = item;
            binding.setItem(item);
        }
    }

    public interface OnClickListener {
        void onClick(DrugItem item);
    }

    public SearchSuggestionsAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnClickListener onClickListener;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final DrugItem item = getItem(position);
        viewHolder.setViewModel(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final DrugSearchSuggestionItemBinding binding = DrugSearchSuggestionItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(viewHolder.item);
            }
        });

        return viewHolder;
    }

    public List<DrugItem> getItems() {
        return getCurrentList();
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public static final DiffUtil.ItemCallback<DrugItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DrugItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull DrugItem oldItem, @NonNull DrugItem newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull DrugItem oldItem, @NonNull DrugItem newItem) {
                    return oldItem.getId().equals(newItem.getId()); // should be fixed
                }
            };
}
