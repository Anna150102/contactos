import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_celular.ActivityAgregar
import com.example.agenda_celular.ActivityEdit
import com.example.agenda_celular.MainActivity
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.databinding.ItemBinding
import com.example.agenda_celular.listPersona
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
        var persona = datalist[position]


        holder.binding.tvNombre.text = persona.name
        holder.binding.tvTelefono.text = persona.phone
        holder.binding.tvEdad.text = persona.age.toString()

        holder.binding.ibEditar.setOnClickListener {
            var intent = Intent(holder.binding.root.context, ActivityEdit::class.java)
            intent.putExtra("id", persona.id.toInt())
            intent.putExtra("name", persona.name.toString())
            intent.putExtra("phone", persona.phone.toString())
            intent.putExtra("age", persona.age.toString())
            listPersona.isEdit = true
            listPersona.personaSelect = persona
            holder.binding.root.context.startActivity(intent)
        }
        holder.binding.ibEliminar.setOnClickListener{
         listPersona.personaSelect = persona
           viewModel.deletePersona(listPersona.personaSelect)
        }
    }
}