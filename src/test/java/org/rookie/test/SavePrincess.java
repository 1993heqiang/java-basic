package org.rookie.test;

public class SavePrincess {
    private static int maxPath;
    private static int rowNum;
    private static int colNum;

    public int SSaveP(char[][] visited, int t, int m, int n) {
        maxPath = t;
        rowNum = m;
        colNum = n;
        int curRowNum = 0, curColNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 'S') {
                    curRowNum = i;
                    curColNum = j;
                }
            }
        }
        visited[curRowNum][curColNum] = '*';
        return traverse(visited, curRowNum, curColNum, 0);
    }

    private int traverse(char[][] origin, int curRowNum, int curColNum, int path) {
        if (maxPath == path) {
            return -1;
        }
        if(curRowNum-1>=0){
            if(origin[curRowNum-1][curColNum]=='.'){
                origin[curRowNum-1][curColNum] = '*';
                int result = traverse(origin,curRowNum-1,curColNum,path+1);
                if(result==-1){
                    origin[curRowNum-1][curColNum] = '.';
                }else {
                    return 0;
                }
            }else if(origin[curRowNum-1][curColNum]=='P'){
                return 0;
            }
        }
        if(curColNum+1<colNum){
            if(origin[curRowNum][curColNum+1]=='.'){
                origin[curRowNum][curColNum+1] = '*';
                int result = traverse(origin,curRowNum,curColNum+1,path+1);
                if(result==-1){
                    origin[curRowNum][curColNum+1] = '.';
                }else {
                    return 0;
                }
            }else if(origin[curRowNum][curColNum+1]=='P'){
                return 0;
            }
        }
        if(curRowNum+1<rowNum) {
            if (origin[curRowNum + 1][curColNum] == '.') {
                origin[curRowNum + 1][curColNum] = '*';
                int result = traverse(origin, curRowNum + 1, curColNum, path + 1);
                if (result == -1) {
                    origin[curRowNum + 1][curColNum] = '.';
                } else {
                    return 0;
                }
            } else if (origin[curRowNum + 1][curColNum] == 'P') {
                return 0;
            }
        }
        if(curColNum-1>=0){
            if(origin[curRowNum][curColNum-1]=='.'){
                origin[curRowNum][curColNum-1] = '*';
                int result = traverse(origin,curRowNum,curColNum-1,path+1);
                if(result==-1){
                    origin[curRowNum][curColNum-1] = '.';
                }else {
                    return 0;
                }
            }else if(origin[curRowNum][curColNum-1]=='P'){
                return 0;
            }
        }
        return -1;
    }

/*    public static void main(String[] args){
        SavePrincess savePrincess = new SavePrincess();
        char[][] visited = {{'.','S','*','.'},
                {'.','*','*','.'},{'.','.','*','.'},{'.','.','P','.'}};
        Instant start = Instant.now();
        int result = savePrincess.SSaveP(visited,5,4,4);
        Instant end = Instant.now();
        System.out.println(result);
        System.out.println(Duration.between(start,end).getNano());
    }*/

}