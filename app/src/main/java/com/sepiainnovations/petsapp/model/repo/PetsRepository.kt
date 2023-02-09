package com.sepiainnovations.petsapp.model.repo

import android.content.res.AssetManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.sepiainnovations.petsapp.model.data.*
import com.sepiainnovations.petsapp.model.network.ApiInterface
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.safety.Whitelist
import org.jsoup.select.Elements
import java.io.IOException
import javax.inject.Inject


class PetsRepository @Inject constructor(
    private val assetManger: AssetManager,
    private val apiInterface: ApiInterface
) {

    /**
     * Retrieve petList from json file
     */
    fun retrievePetList(): List<PetDetails>?{
        lateinit var jsonString: String
        try {
            jsonString = assetManger.open("pets_list.json")
                .bufferedReader(Charsets.UTF_8)
                .use {
                    it.readText()
                }
        } catch (ignore: IOException) {
            return null
        }
        val parsedJson = JsonParser.parseString(jsonString)
        val pets = Gson().fromJson(parsedJson, PetsList::class.java)
        return pets.pets
    }

    /**
     * Retrieve the article paragraph using wikipedia api
     */
    suspend fun retrieveContentDescriptionWikiApi(contentName: String): Result<WikiResponse>{
        return apiInterface.getDescription(contentName)
    }

    /**
     * Retrieve the article paragraph using Jsoup library.
     *
     */
    fun retrieveUsingSomething(contentUrl: String): String?{
        return try {
            val doc: Document = Jsoup.connect(contentUrl).get()
            val paragraphs: Elements = doc.select(".mw-content-ltr p")
            Jsoup.clean(paragraphs.html(), Whitelist.basic())
        }catch (e: Exception){
            null
        }
    }

}