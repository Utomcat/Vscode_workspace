#include <stdio.h>

int main() {
    // 定义声明一个变量,并初始化
    int price = 0;
    printf("请输入金额(元): \n");
    scanf("%d",&price);
    int change = 100 - price;
    printf("找零 %d 元\n",change);
    return 0;
}