import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_celular.ActivityAgregar
import com.example.agenda_celular.ActivityEdit
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.databinding.ItemBinding
import com.example.agenda_celular.viewModel.PersonaViewModel


class PersonaAdapter(var datalist: List<PersonaModel>, var viewModel: PersonaViewModel) : RecyclerView.Adapter<PersonaAdapter.PersonaHolder>() {

    inner class PersonaHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaHolder(binding)
    }

    override fun getItemCount(): Int {
       return datalist.size
    }

    override fun onBindViewHolder(holder: PersonaHolder, position: Int) {
        var item = datalist[position]


        holder.binding.tvNombre.text = item.name
        holder.binding.tvTelefono.text = item.phone
        holder.binding.tvEdad.text = item.age.toString()

        holder.binding.ibEditar.setOnClickListener {
            var intent = Intent(holder.binding.root.context, ActivityEdit::class.java)
            intent.putExtra("id", item.id.toInt())
            intent.putExtra("nombre", item.name.toString())
            intent.putExtra("telefono", item.phone.toString())
            intent.putExtra("edad", item.age.toString())
            holder.binding.root.context.startActivity(intent)
        }
        holder.binding.ibEliminar.setOnClickListener{
            viewModel.deletePersona(item)
        }
    }
}