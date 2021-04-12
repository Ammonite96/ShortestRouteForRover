import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RoutFinderImpl implements RouteFinder {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private CoordinatePoint startPoint;
    private CoordinatePoint endPoint;
    private boolean[][] visited;

    @Override
    public char[][] findRoute(char[][] map) {
        return printPath(bfs(map), map);
    }

    //Обход графа в ширину
    private List<CoordinatePoint> bfs(char[][] map) {
        LinkedList<CoordinatePoint> visitVortex = new LinkedList<>();
        getStartPointAndEndPoint(map);
        visitVortex.add(startPoint);
        visited = new boolean[map.length][map[0].length];
        while (!visitVortex.isEmpty()) {
            CoordinatePoint currentPoint = visitVortex.remove();

            if (!isValidLocation(currentPoint.getX(), currentPoint.getY(), map) || isExplored(currentPoint.getX(), currentPoint.getY())) {
                continue;
            }

            if (!isBarrier(currentPoint.getX(), currentPoint.getY(), map)) {
                setVisited(currentPoint.getX(), currentPoint.getY(), true);
                continue;
            }

            if (currentPoint.equals(endPoint)) {
                return backPatch(currentPoint);
            }

            for (int[] direction : DIRECTIONS) {
                CoordinatePoint point = new CoordinatePoint(currentPoint.getX() + direction[0], currentPoint.getY() + direction[1], currentPoint);
                visitVortex.add(point);
                setVisited(currentPoint.getX(), currentPoint.getY(), true);
            }
        }
        return null;
    }

    //Рисуем путь
    private char[][] printPath(List<CoordinatePoint> bfs, char[][] map) {
        char[][] temp = Arrays.stream(map)
                .map(char[]::clone)
                .toArray(char[][]::new);
        for (CoordinatePoint coordinate : bfs) {
            if (coordinate.equals(startPoint) || coordinate.equals(endPoint)) {
                continue;
            }
            temp[coordinate.getX()][coordinate.getY()] = '+';
        }
        return temp;
    }

    // метод писка стартовой и конечной точки
    private void getStartPointAndEndPoint(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                char c = map[i][j];
                if (c == '@') {
                    startPoint = new CoordinatePoint(i, j);
                }
                if (c == 'X') {
                    endPoint = new CoordinatePoint(i, j);
                }
            }
        }
    }

    private boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    private void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    private boolean isValidLocation(int row, int col, char[][] map) {
        if (row <= 0 || row >= map.length || col <= 0 || col >= map[0].length) {
            return false;
        }
        return true;
    }

    private boolean isBarrier(int row, int col, char[][] map) {
        if (map[row][col] == '#') {
            return false;
        }
        return true;
    }

    private List<CoordinatePoint> backPatch(CoordinatePoint currentPoint) {
        List<CoordinatePoint> path = new ArrayList<>();
        CoordinatePoint point = currentPoint;
        while (point != null) {
            path.add(point);
            point = point.getParent();
        }
        return path;
    }
}
