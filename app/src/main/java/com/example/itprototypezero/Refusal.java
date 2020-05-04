package com.example.itprototypezero;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class Refusal extends AppCompatDialogFragment {
    private TextView refuseQuery;
    private EditText refuseReason;
    private RefusalDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.refuse_task_reason,null);

        builder.setView(view)
                .setTitle("Refusal Reason")
                .setNegativeButton("cancel",new DialogInterface.OnClickListener(){
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        String reason = refuseReason.getText().toString();
                        listener.applyTexts(reason);
                    }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        refuseQuery = view.findViewById(R.id.refuseQuery);
        refuseReason =  view.findViewById(R.id.refuseReason);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (RefusalDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "must implement RefusalDialogListener");
        }
    }

    public interface RefusalDialogListener{
        void applyTexts(String reason);
    }
}
