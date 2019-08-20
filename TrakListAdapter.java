package app.com.imeitransaction;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TrakListAdapter extends RecyclerView.Adapter<TrakListAdapter.MemberViewHolder> {

    private List<TransactionDetails> members;
    private Context context;
    String strUser="";
    String name; String mobile; String dob; String idProof; String cardno; String cvv; String expiredate; String upi; String amount; String remark; String otp;

    public TrakListAdapter(List<TransactionDetails> members, Context context) {
        this.members = members;
        this.context=context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

       TextView editname;

        TextView editphone;

        TextView editdob;

        TextView etidproof;

        TextView etcardno;

        TextView etcvv;

        TextView etexpdate;

        TextView etupi;

        TextView etamt;

        TextView etremark;


        public MemberViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv);
            editname = (TextView)itemView.findViewById(R.id.editname);
            editphone = (TextView) itemView.findViewById(R.id.editphone);
            editdob = (TextView) itemView.findViewById(R.id.editdob);
            etidproof = (TextView) itemView.findViewById(R.id.etidproof);
            etcardno=(TextView) itemView.findViewById(R.id.etcardno);
            etcvv=(TextView) itemView.findViewById(R.id.etcvv);
            etexpdate=(TextView) itemView.findViewById(R.id.etexpdate);
            etupi=(TextView) itemView.findViewById(R.id.etupi);
            etamt=(TextView) itemView.findViewById(R.id.etamt);
            etremark=(TextView) itemView.findViewById(R.id.etremark);
        }
    }


    @Override
    public void onBindViewHolder(MemberViewHolder memberViewHolder, final int i) {

        memberViewHolder.editname.setText("Name : "+members.get(i).getName());
        memberViewHolder.editphone.setText("Phone : "+members.get(i).getMobile());
        memberViewHolder.editdob.setText("Dob : "+members.get(i).getDob());

        memberViewHolder.etidproof.setText("IdProof : "+members.get(i).getIdProof());
        memberViewHolder.etcardno.setText("Card No : "+members.get(i).getCardno());
        memberViewHolder.etcvv.setText("Cvv : "+members.get(i).getCvv());

        memberViewHolder.etexpdate.setText("Expire Date : "+members.get(i).getExpiredate());
        memberViewHolder.etupi.setText("UPI : "+members.get(i).getUpi());

        memberViewHolder.etamt.setText("Amount : "+members.get(i).getAmount());
        memberViewHolder.etremark.setText("Remarks: "+members.get(i).getRemark());

        final int pos=i;
        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //String series=members.get(i).getSeries();
             //  Toast.makeText(context, "Series : "+series,Toast.LENGTH_LONG).show();
               // Intent intent=new Intent(context,TrackBusLocation.class);
               // intent.putExtra("Series",series);
              //  intent.putExtra("UName",members.get(i).getName());
              //  context.startActivity(intent);

            }
        });



    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_list_adapter, viewGroup, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
