import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_celular.PersonaModel
import com.example.agenda_celular.databinding.ItemBinding


class PersonaAdapter(var datalist: List<PersonaModel>) : RecyclerView.Adapter<PersonaAdapter.PersonaHolder>() {

    inner class PersonaHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaHolder, position: Int) {
        var item = datalist[position]
        holder.binding.tvNombre.text = item.name
        holder.binding.tvEdad.text = item.age.toString()
        holder.binding.tvTelefono.text = item.phone

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

}