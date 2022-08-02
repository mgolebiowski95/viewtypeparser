package com.example.app.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app.R
import com.example.app.databinding.DialogLayoutChooserBinding

class LayoutChooser : AbstractDialogFragment() {
    private lateinit var binding: DialogLayoutChooserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogLayoutChooserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext(), androidx.recyclerview.widget.RecyclerView.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)

        val directory = "layouts"
        val layouts = resources.assets.list(directory)!!
        val adapter = Adapter(
            layouts.map { Item(it, "$directory/$it") },
            object : OnItemClickListener {
                override fun onItemClickListener(item: Item) {
                    setResultCode(DialogFragmentCallback.RESULT_OK)
                    val data = Bundle()
                    data.putString("path", item.path)
                    setData(data)
                    dismiss()
                }
            })
        binding.recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    data class Item(val displayName: String, val path: String)

    interface OnItemClickListener {
        fun onItemClickListener(item: Item)
    }

    class ViewHolder(parent: ViewGroup, itemClickListener: OnItemClickListener) : androidx.recyclerview.widget.RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dialog_layout_chooser_item, parent, false)) {
        val textView = itemView.findViewById<TextView>(R.id.text_view)

        lateinit var item: Item

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClickListener(item)
            }
        }
    }

    class Adapter(val items: List<Item>, val itemClickListener: OnItemClickListener) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
            return ViewHolder(parent, itemClickListener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.item = item

            holder.textView.text = item.displayName
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }

    companion object {
        fun newInstance(requestCode: Int): LayoutChooser {
            val fragment = LayoutChooser()
            val args = Bundle()
            args.putInt(KEY_REQUEST_CODE, requestCode)
            fragment.arguments = args
            return fragment
        }
    }
}