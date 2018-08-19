package odlare.com.pontinho;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlayerListAdapter extends  RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>{

    private static final String S3_BASE_URL = "https://s3.us-east-2.amazonaws.com/segurofacil/pontinho/";

    private Context context;
    private List<Player> players;

    public PlayerListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        if (!players.isEmpty()) {
            Player player = players.get(position);
            holder.name.setText(String.format("%s %s", player.getName(), player.getLastname()));
            holder.email.setText(player.getEmail());
            holder.phone.setText(player.getPhone());
            Glide.with(holder.itemView).load(S3_BASE_URL + player.getName().toLowerCase() + ".jpg").into(holder.photo);
        }
    }

    @Override
    public int getItemCount() {
        return players != null ? players.size() : 0;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public Player getPlayerAtPosition(int position) {
        return this.players.get(position);
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phone;
        private TextView email;
        private ImageView photo;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.player_name);
            this.phone = itemView.findViewById(R.id.player_phone);
            this.email = itemView.findViewById(R.id.player_email);
            this.photo = itemView.findViewById(R.id.player_photo);
        }
    }
}