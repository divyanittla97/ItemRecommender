import java.io.*;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
public class Item_recommender {
public static void main(String args[])throws IOException, TasteException{
	
	DataModel model = new FileDataModel(new File("/users/divyanittla/Downloads/dataset.csv"));
	ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
	GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);
	int x=1;
	for(LongPrimitiveIterator items = model.getItemIDs(); items.hasNext();) {
		long itemId = items.nextLong();
		List<RecommendedItem>recommendations = recommender.mostSimilarItems(itemId, 5);
		
		for(RecommendedItem recommendation : recommendations) {
			System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
		}
		x++;
	}
	
}
}
