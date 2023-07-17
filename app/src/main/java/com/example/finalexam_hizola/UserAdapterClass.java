package com.example.finalexam_hizola;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapterClass extends RecyclerView.Adapter<UserAdapterClass.ViewHolder>{

    List<UserModelClass> user;
    Context context;
    myDbAdapter myUserDbAdapter;

    public UserAdapterClass(List<UserModelClass> user, Context context) {
        this.user = user;
        this.context = context;
        myUserDbAdapter = new myDbAdapter(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view = layoutinflater.inflate(R.layout.user_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final UserModelClass userModelClass = user.get(position);

        holder.textViewID.setText(Integer.toString(userModelClass.getId()));
        holder.editText_userName.setText(userModelClass.getName());
        holder.editText_userPass.setText(userModelClass.getPassword());

        holder.button_userEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = holder.editText_userName.getText().toString();
                String userPass = holder.editText_userPass.getText().toString();

                myUserDbAdapter.updateUser(new UserModelClass(userModelClass.getId(), userName, userPass));
                notifyDataSetChanged();
                ((Activity)context).finish();
                context.startActivity(((Activity)context).getIntent());
            }
        });

        holder.button_userDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUserDbAdapter.deleteUser(userModelClass.getId());
                user.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_userName;
        EditText editText_userPass;
        Button button_userEdit;
        Button button_userDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.user_id);
            editText_userName = itemView.findViewById(R.id.user_name_et);
            editText_userPass = itemView.findViewById(R.id.user_pass_et);
            button_userEdit = itemView.findViewById(R.id.edit_btn);
            button_userDelete = itemView.findViewById(R.id.delete_btn);

        }
    }
}
