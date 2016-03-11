package HackerRank;

/**
 * Created by vppriyad on 10/19/2015.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Comparator;

public class MovieRec {

    public static class Movie {
        private final int movieId;
        private final float rating;
        private List<Movie> similarMovies; // Similarity is bidirectional

        public Movie(int movieId, float rating) {
            this.movieId = movieId;
            this.rating = rating;
            similarMovies = new ArrayList<Movie>();
        }

        public int getId() {
            return movieId;
        }

        public float getRating() {
            return rating;
        }

        public void addSimilarMovie(Movie movie) {
            similarMovies.add(movie);
            movie.similarMovies.add(this);
        }

        public List<Movie> getSimilarMovies() {
            return similarMovies;
        }
    }
    /*
        * @param movie Current movie.
        * @param numTopRatedSimilarMovies the maximum number of recommended movies to return.
        * @return List of top rated similar movies.
        *
        * Assumptions I made:
        * each movie has unique movie id.
        *
        * Description of my approach: Do a BFS of the graph connecting similar movies startign from Movie.
        * for each Movie processed, add it to a minHeap.
        * if size of minHeap exceeds the total numTopRatedSimilarMovies requested, delete min from the heap.
        * This way the minHeap will have top numTopRatedSimilarMovies of all the similar movies to Movie.
        *
        * Runtime complexity of my approach: O(N * log X) where N is the total numner of movies similar to given Movie.
        * and X is the requested numTopRatedSimilarMovies.
        *
        * Justification of runtime complexity:
        * The solution parses through all  movies simialr to given Movie and maintains the top X movies based on its rating.
        * for maintaining the heap, O(Log X) operations to add and remove element from min Heap. where X is numTopRatedSimilarMovies
        *
        */
    public static List<Movie> getMovieRecommendations(Movie movie, int numTopRatedSimilarMovies) {
        Set<Integer> visitSet = new HashSet<Integer>();

        class RatingComparator implements Comparator<Movie>
        {
            // compare two movies -1 if o1 < o2.
            public int compare(Movie o1, Movie o2) {
                float rating1 = o1.getRating();
                float rating2 = o2.getRating();
                if (rating1 < rating2) return -1;
                if (rating1 > rating2) return 1;
                return 0;
            }
        }

        Queue<Movie> bfsQ = new LinkedList<>();
        Comparator<Movie> ratingComparator = new RatingComparator();
        PriorityQueue<Movie> minHeapofMov = new PriorityQueue<Movie>(numTopRatedSimilarMovies, ratingComparator);
        bfsQ.add(movie);

        Movie curr;
        while(!(bfsQ.isEmpty())){
            // returns null of empty
            curr = bfsQ.poll();
            // mark curr visited.
            visitSet.add(curr.getId());
            for (Movie similar : curr.getSimilarMovies()){
                // if (similar ! visited)
                // and if it is not already present in queue
                if (!(visitSet.contains(similar.getId()))) {
                    if (!(bfsQ.contains(similar))){
                        // use offer instead, returns false if not inserted in the head due to capacity restrictions.
                        bfsQ.add(similar);
                    }
                }
            }
            // do not consider the same movie as root movie
            if (curr.getId() != movie.getId()) {
                if (minHeapofMov.size() < numTopRatedSimilarMovies) {
                    minHeapofMov.add(curr);
                }
                // exceeded size, so adjust by removing the movie with min rating.
                else {
                    // if curr movie is greater than peek min rating Movie, then remove the min movie and insert the curr movie.
                    if (ratingComparator.compare(curr, minHeapofMov.peek()) > 0) {
                        minHeapofMov.poll();
                        minHeapofMov.add(curr);
                    }
                }
            }

            /*// process curr node
            // add it to minHeap that maintains numTopRatedSimilarMovies top movies.
            // do not consider the same movie as root movie
            // if the node is already processed skip.
            if (curr.getId() != movie.getId()){
                if (!(minHeapofMov.isEmpty())){
                    // if the curr movie is greater than peek() then add to the minHeap.
                    // if it is lesser than peek(), it will be the new peek and it will get removed.
                    //if (ratingComparator.compare(minHeapofMov.peek(), curr) < 0){
                        minHeapofMov.add(curr);
                    //}
                    // repeatedly delete min if it exceeds size
                    int size = minHeapofMov.size();
                    if (size > numTopRatedSimilarMovies){
                        // System.out.println("Removing Movie: " + minHeapofMov.poll().getId() + " minHeapofMov.size(): "+ size);
                        minHeapofMov.poll();
                    }
                }
                else{
                    minHeapofMov.add(curr);
                }

            }*/
        }

        List<Movie> listofTopN = new ArrayList<>( minHeapofMov );
        return listofTopN;
    }

    public static void main(String[] args) throws IOException {
        final Map<Integer, Movie> movieMap = new HashMap<Integer, Movie>();
        Movie rootMovie = null;
        int numTopRatedSimilarMovies = 0;

        final Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            final String type = in.next();

            if (type.equals("movie")) {
                final int id = in.nextInt();
                final float rating = in.nextFloat();
                movieMap.put(id, new Movie(id, rating));
            } else if (type.equals("similar")) {
                final Movie movie1 = movieMap.get(in.nextInt());
                final Movie movie2 = movieMap.get(in.nextInt());
                movie1.addSimilarMovie(movie2);
            } else if (type.equals("params")) {
                rootMovie = movieMap.get(in.nextInt());
                numTopRatedSimilarMovies = in.nextInt();
            }
            else if (type.equals("q")) {
                break;
            }
            else
            {
                // ignore comments and whitespace
            }
        }

        final List<Movie> result = getMovieRecommendations(rootMovie, numTopRatedSimilarMovies);


        String output = "result";

        if (result == null) {
            output += " <null>";
        } else {
            Collections.sort(result, new Comparator() {
                @Override
                public int compare(Object m1, Object m2) {
                    return ((Movie)m1).getId() - ((Movie)m2).getId();
                }
            });

            for (Movie m: result) {
                output += " ";
                output += m.getId();
            }
        }

        System.out.println(output);

        /*final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(output);
        bw.newLine();
        bw.close();*/
    }
}