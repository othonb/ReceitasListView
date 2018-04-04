package com.raywenderlich.android.alltherecipes

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecipeAdapter (private val context: Context,
                     private val dataSource: ArrayList<Recipe>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object {

        // Um hash que pareia um label de detalhe de receita com o id de recurso de uma cor
        // definida em colors.xml
        private val LABEL_COLORS = hashMapOf(
                "Low-Carb" to R.color.colorLowCarb,
                "Low-Fat" to R.color.colorLowFat,
                "Low-Sodium" to R.color.colorLowSodium,
                "Medium-Carb" to R.color.colorMediumCarb,
                "Vegetarian" to R.color.colorVegetarian,
                "Balanced" to R.color.colorBalanced
        )
    }

    // Faz com que ListView saiba quantos itens mostrar, ou em outras palavras,
    // o tamanho da fonte de dados.
    override fun getCount(): Int {
        return dataSource.size
    }

    // Retorna um item a ser colocado em uma dada posição da fonte de dados, especificamente,
    // objetos Recipe obtidos de dataSource.
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    // Esse método define um ID único para cada linha da lista.
    // Por simplicidade, você apenas usa a posição do item como seu ID.
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Cria uma view para ser usada como uma linha da lista. Aqui você define qual informação é
    // exibida e em que lugar ela fica na ListView. Você também infla uma view personalizada de um
    // layout em XML definida em res/layout/list_item_recipe.xml
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Recupera a view para um item de linha
        val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)

        // Recupera o elemento título
        val titleTextView = rowView.findViewById (R.id.recipe_list_title) as TextView

        // Recupera o elemento subtítulo
        val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView

        // Recupera o elemento detalhe
        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView

        // Recupera o elemento imagem
        val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView

        // Recupera a receita correspondente para a linha atual
        val recipe = getItem(position) as Recipe

        // Atualiza os text views da view da linha para que exibam a receita.
        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

        // Faz uso da biblioteca de código aberto Picasso para carregar imagens assincronamente
        // - ajuda se você baixar a imagem em uma thread separada ao invés de usar a thread
        // principal. Você também está atribuindo um marcardor temporário para o ImageView para
        // manipular o carregamento lento de imagens.
        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        // Muda as fontes dos Text Views
        val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
        titleTextView.typeface = titleTypeFace

        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
        subtitleTextView.typeface = subtitleTypeFace

        val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
        detailTextView.typeface = detailTypeFace

        // Muda a cor do detalhe conforme o hash pré-definido
        detailTextView.setTextColor(
                ContextCompat.getColor(context, LABEL_COLORS[recipe.label] ?: R.color.colorPrimary))

        return rowView
    }

}