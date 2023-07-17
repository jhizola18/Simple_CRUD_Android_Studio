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

public class AdminAdapterClass extends RecyclerView.Adapter<AdminAdapterClass.ViewHolder>{

    List<AdminModelClass> admin;
    Context context;

    myAdminDbAdapter myAdminDbAdapter;

    public AdminAdapterClass(List<AdminModelClass> admin, Context context) {
        this.admin = admin;
        this.context = context;
        myAdminDbAdapter = new myAdminDbAdapter(context);
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view = layoutinflater.inflate(R.layout.admin_item_list,parent,false);
        ViewHolder viewHolderAdmin = new ViewHolder(view);


        return viewHolderAdmin;

    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapterClass.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final AdminModelClass adminModelClass = admin.get(position);

        holder.textViewID.setText(Integer.toString(adminModelClass.getAdmin_id()));
        holder.editText_adminName.setText(adminModelClass.getAdmin_name());
        holder.editText_adminPass.setText(adminModelClass.getAdmin_password());

        holder.button_adminEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminName = holder.editText_adminName.getText().toString();
                String adminPass = holder.editText_adminPass.getText().toString();

                myAdminDbAdapter.updateAdmin(new AdminModelClass(adminModelClass.getAdmin_id(), adminName, adminPass));
                notifyDataSetChanged();
                ((Activity)context).finish();
                context.startActivity(((Activity)context).getIntent());
            }
        });

        holder.button_adminDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdminDbAdapter.deleteAdmin(adminModelClass.getAdmin_id());
                admin.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        return admin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewID;
        EditText editText_adminName;
        EditText editText_adminPass;
        Button button_adminEdit;
        Button button_adminDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.admin_id);
            editText_adminName = itemView.findViewById(R.id.Admin_name_et);
            editText_adminPass = itemView.findViewById(R.id.admin_pass_et);
            button_adminEdit = itemView.findViewById(R.id.admin_edit_btn);
            button_adminDelete = itemView.findViewById(R.id.admin_delete_btn);


        }
    }
}
