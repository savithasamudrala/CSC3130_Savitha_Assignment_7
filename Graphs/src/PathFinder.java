import java.util.*;

public class PathFinder {
    private final Map<Integer, List<Edge>> graph;
    private static class Edge {
        int target;
        int weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public PathFinder(int numberOfVertices) {
        graph = new HashMap<>();
        for (int i = 0; i < numberOfVertices; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int target, int weight) {
        graph.get(source).add(new Edge(target, weight));
    }

    public List<List<Integer>> findPaths(int u, int w, int pathLength) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        path.add(u);
        dfs(u, w, 0, pathLength, path, visited, allPaths);
        return allPaths;
    }

    private void dfs(int current, int destination, int currentLength, int pathLength, List<Integer> path, Set<Integer> visited, List<List<Integer>> allPaths) {
        if (current == destination && currentLength == pathLength) {
            allPaths.add(new ArrayList<>(path));
            return;
        }
        if (currentLength >= pathLength) {
            return;
        }
        visited.add(current);
        for (Edge neighbor : graph.get(current)) {
            if (!visited.contains(neighbor.target)) {
                path.add(neighbor.target);
                dfs(neighbor.target, destination, currentLength + neighbor.weight, pathLength, path, visited, allPaths);
                path.remove(path.size() - 1);
            }
        }
        visited.remove(current);
    }

    public static void main(String[] args) {
        // Test Case 1: Simple Direct Path
        System.out.println("Test Case 1: Simple Direct Path");
        PathFinder pf1 = new PathFinder(2);
        pf1.addEdge(0, 1, 5);
        printPaths(pf1.findPaths(0, 1, 5));

        // Test Case 2: Multiple Paths, Same Length
        System.out.println("Test Case 2: Multiple Paths, Same Length");
        PathFinder pf2 = new PathFinder(3);
        pf2.addEdge(0, 1, 2);
        pf2.addEdge(1, 2, 3);
        pf2.addEdge(0, 2, 5);
        printPaths(pf2.findPaths(0, 2, 5));

        // Test Case 3: No Valid Paths
        System.out.println("Test Case 3: No Valid Paths");
        PathFinder pf3 = new PathFinder(3);
        pf3.addEdge(0, 1, 3);
        pf3.addEdge(1, 2, 4);
        printPaths(pf3.findPaths(0, 2, 5));

        // Test Case 4: Complex Graph With Backtracking
        System.out.println("Test Case 4: Complex Graph With Backtracking");
        PathFinder pf4 = new PathFinder(4);
        pf4.addEdge(0, 1, 1);
        pf4.addEdge(1, 2, 2);
        pf4.addEdge(2, 3, 2);
        pf4.addEdge(3, 0, 3);
        pf4.addEdge(1, 3, 4);
        pf4.addEdge(0, 3, 5);
        printPaths(pf4.findPaths(0, 3, 5));
    }

    private static void printPaths(List<List<Integer>> paths) {
        if (paths.isEmpty()) {
            System.out.println("No paths found.");
        } else {
            for (List<Integer> path : paths) {
                System.out.println(path);
            }
        }
        System.out.println();
    }
}
