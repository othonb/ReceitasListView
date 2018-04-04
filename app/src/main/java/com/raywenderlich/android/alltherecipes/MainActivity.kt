/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.raywenderlich.android.alltherecipes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

  private lateinit var listView : ListView

  override fun onCreate(savedInstanceState: Bundle?) {

      super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_main)

      // 0 - Liga o ListView da tela à variável de instância listView
      listView = findViewById<ListView>(R.id.recipe_list_view)

      // 1 - Lê um ArrayList de objetos Recipe para recipeList
      val recipeList = Recipe.getRecipesFromFile("recipes.json", this)

      val adapter = RecipeAdapter(this, recipeList)

      listView.adapter = adapter

      val context = this
      listView.setOnItemClickListener { _, _, position, _ ->

          // Recupera o objeto recipe para a linha que foi clicada
          val selectedRecipe = recipeList[position]

          // Cria um Intent para navegar para seu RecipeDetailActivity para exibir mais informação
          val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)

          // Inicia a RecipeDetailActivity passando o objeto Intent qur você criou
          // ao método startActivity().
          startActivity(detailIntent)
      }


      // 2 - Cria um vetor de String do mesmo tamanho que o ArrayList recipeList
      //val listItems = arrayOfNulls<String>(recipeList.size)

      // 3 - Preenche o vetor com o title de cada objeto do ArrayList
      //for (i in 0 until recipeList.size) {

        //  val recipe = recipeList[i]

          //listItems[i] = recipe.title

      //}

      // 4 - Cria um ArrayAdapter e o atribui ao ListView através da variável listView
      //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

      //listView.adapter = adapter as ListAdapter?


  }
}
