package kr.ac.cbnu.saengsaengyaktong.ui.medicine;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.databinding.SearchSuggestionItemBinding;

public class SearchSuggestionsAdapter extends ListAdapter<SearchSuggestion, SearchSuggestionsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final SearchSuggestionItemBinding binding;
        private SearchSuggestion item;

        public ViewHolder(SearchSuggestionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void setItem(SearchSuggestion item) {
            this.item = item;
            binding.setItem(item);
        }
    }

    public interface OnClickListener {
        void onClick(SearchSuggestion item);
    }

    private OnClickListener onClickListener;

    public SearchSuggestionsAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setItems(List<? extends SearchSuggestion> items) {
        submitList((List<SearchSuggestion>) items);
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final SearchSuggestion item = getItem(position);
        viewHolder.setItem(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final SearchSuggestionItemBinding binding = SearchSuggestionItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(viewHolder.item);
            }
        });

        return viewHolder;
    }

    public static final DiffUtil.ItemCallback<SearchSuggestion> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SearchSuggestion>() {
                @Override
                public boolean areItemsTheSame(@NonNull SearchSuggestion oldItem, @NonNull SearchSuggestion newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull SearchSuggestion oldItem, @NonNull SearchSuggestion newItem) {
                    return oldItem.getId().equals(newItem.getId()); // should be fixed
                }
            };
}
