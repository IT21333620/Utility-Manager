package com.example.utilitymanager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.models.usage_update
import com.google.firebase.database.*

class fuel_recyclerView : AppCompatActivity(), FuelAdapter.OnItemClickListener  {

    private lateinit var dbRef : DatabaseReference
    private lateinit var fuelRecyclerView: RecyclerView
    private lateinit var weekList : ArrayList<FuelModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_recycler_view)

        initView()
        setValuesToViews()

        fuelRecyclerView = findViewById(R.id.weekList)
        fuelRecyclerView.layoutManager = LinearLayoutManager(this)
        fuelRecyclerView.setHasFixedSize(true)

        weekList = arrayListOf<FuelModel>()
        getUserData()
    }

    private fun initView() {}

    private fun setValuesToViews() {}

    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Fuel_Usage")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                weekList.clear()

                if(snapshot.exists()){
                    for(fuelSnapshot in snapshot.children){
                        val fuel = fuelSnapshot.getValue(FuelModel::class.java)
                        weekList.add(fuel!!)

                    }

                    fuelRecyclerView.adapter = FuelAdapter(weekList, this@fuel_recyclerView)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    override fun onUpdateClick(fuel: FuelModel) {
        val intent = Intent(this, usage_update::class.java)
        intent.putExtra("fuelId", fuel.fuelId)
        startActivity(intent)
    }

    override fun onDeleteClick(fuel: FuelModel) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Fuel_Usage").child(fuel.fuelId.toString())
        dbRef.removeValue().addOnSuccessListener {
            Toast.makeText(this, "Fuel data deleted", Toast.LENGTH_SHORT).show()
            // Remove the deleted item from the weekList
            weekList.remove(fuel)
            // Notify the adapter about the change
            fuelRecyclerView.adapter?.notifyDataSetChanged()
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Failed to delete fuel data: $error", Toast.LENGTH_SHORT).show()
        }
    }

}