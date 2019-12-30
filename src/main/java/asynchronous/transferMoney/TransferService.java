package asynchronous.transferMoney;



import java.util.concurrent.CompletableFuture;

/**
 * 转账服务
 */
public interface TransferService {
    /**
     * 异步转账服务
     *
     * @param fromAccount 转出账户
     * @param toAccount   转入账户
     * @param amount      转账金额，单位分
     */
//    可以看到这两个接口中定义的方法的返回类型都是一个带泛型的 CompletableFuture，
//    尖括号中的泛型类型就是真正方法需要返回数据的类型，
//    我们这两个服务不需要返回数据，所以直接用 Void 类型就可以。
    CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount);
}
