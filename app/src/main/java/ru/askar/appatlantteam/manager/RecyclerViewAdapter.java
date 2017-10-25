package ru.askar.appatlantteam.manager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.askar.appatlantteam.R;
import ru.askar.appatlantteam.models.User;

/**
 * Created by Сайида on 24.10.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<User> records;

    public RecyclerViewAdapter(List<User> users) {
        this.records = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = records.get(position);
        holder.name.setText(user.getName());
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        holder.website.setText(user.getWebsite());
        holder.street.setText("street: " + user.getAddress().getStreet());
        holder.suite.setText("suite: " + user.getAddress().getSuite());
        holder.city.setText("city: " + user.getAddress().getCity());
        holder.zipcode.setText("zipcode: " + user.getAddress().getZipcode());
        holder.companyName.setText(user.getCompany().getName());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView username;
        private TextView email;
        private TextView phone;
        private TextView website;
        private TextView street;
        private TextView suite;
        private TextView city;
        private TextView zipcode;
        private TextView companyName;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            username = (TextView) itemView.findViewById(R.id.tvUserName);
            email = (TextView) itemView.findViewById(R.id.tvMail);
            phone = (TextView) itemView.findViewById(R.id.tvPhone);
            website = (TextView) itemView.findViewById(R.id.tvWebsite);
            street = (TextView) itemView.findViewById(R.id.tvStreet);
            suite = (TextView) itemView.findViewById(R.id.tvSuite);
            city = (TextView) itemView.findViewById(R.id.tvCity);
            zipcode = (TextView) itemView.findViewById(R.id.tvZipcode);
            companyName = (TextView) itemView.findViewById(R.id.tvCompany);
        }
    }
}
