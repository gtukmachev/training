package tga.Trapping_Rain_Water

class Solution {
    fun trap(height: IntArray): Int {
        var li = 0;
        var lh = height[0];

        var ri = height.size-1
        var rh = height[ri]

        var v = 0

        while ( (ri-li) > 1) {
            if (lh < rh){
                li++
                if (height[li] > lh) lh = height[li]
                else  v += lh - height[li]
            } else {
                ri--
                if (height[ri] > rh) rh = height[ri]
                else  v += rh-height[ri]
            }
        }

        return v

    }
}