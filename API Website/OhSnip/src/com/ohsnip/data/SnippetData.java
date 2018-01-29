package com.ohsnip.data;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ohsnip.model.Snippet;

public class SnippetData {

		static SnippetData sd = null;
		static MongoCollection<Snippet> colSnip;
		
		
		public static SnippetData getInstance() {
			
			if (sd == null) {
				sd = new SnippetData();
				
				String uri = "mongodb://ruigomespt:ruigomes43@snipcluster-shard-00-00-dcw8c.mongodb.net:27017,snipcluster-shard-00-01-dcw8c.mongodb.net:27017,snipcluster-shard-00-02-dcw8c.mongodb.net:27017/admin?replicaSet=SnipCluster-shard-0&ssl=true";
				MongoClientURI clientURI = new MongoClientURI(uri);
				MongoClient mongoClient = new MongoClient(clientURI);

				MongoDatabase mongoDatabase = mongoClient.getDatabase("OhSnip");
				colSnip = mongoDatabase.getCollection("Snippets", Snippet.class);
				
				
			}
			
			return sd;
			
		}
		
		public void newSnippet(Snippet snippet) {
			colSnip.insertOne(snippet);
		}
		
		public Snippet getSnippet(int id) {
			System.out.println("ola");
			return null;

		}
		
		public List<Snippet> getData() {
			System.out.println("gato");
			final List<Snippet> snippets = new ArrayList<Snippet>();
			
			Block<Snippet> printBlock = new Block<Snippet>() {
			    public void apply(final Snippet snippet) {
			    	snippets.add(snippet);
			    }
			};

			colSnip.find().forEach(printBlock);
			return snippets;
		}
		
		
		
		public List<Snippet> getData(String tags) {
			final List<Snippet> snippets = new ArrayList<Snippet>();
			
			Block<Snippet> printBlock = new Block<Snippet>() {
			    public void apply(final Snippet snippet) {
			    	snippets.add(snippet);
			    }
			};

			colSnip.find(eq("tags",tags)).forEach(printBlock);;		
			return snippets;
		}
		
		public void removeData(int id) {
			colSnip.deleteOne(eq("id_snippet", id));		
		}
}
