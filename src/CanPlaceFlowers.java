public class CanPlaceFlowers {
    public static void main(String[] args){
        CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();
        System.out.println(canPlaceFlowers.canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        /*
        Example 1:
        Input: flowerbed = [1,0,0,0,1], n = 1
        Output: true
        Example 2:
        Input: flowerbed = [1,0,0,0,1], n = 2
        Output: false
         */
        boolean bool = false;
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (i == 0 || i == (flowerbed.length - 1)) {
                int j = flowerbed[i];
                if (j == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            } else {
                int before = flowerbed[i - 1];
                int after = flowerbed[i + 1];
                if (before == 0 && after == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        if(count == n){
            bool = true;
        }

        return bool;
    }
}
