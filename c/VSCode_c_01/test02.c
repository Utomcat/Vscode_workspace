#include <stdio.h>

int main() {
    // ��������һ������,����ʼ��
    int price = 0;
    printf("��������(Ԫ): \n");
    scanf("%d",&price);
    int change = 100 - price;
    printf("���� %d Ԫ\n",change);
    return 0;
}