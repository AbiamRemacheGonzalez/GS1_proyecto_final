package GS1.View;

import GS1.Model.Balance;

public interface BalanceLoader {
    public Balance getUserBalance(int groupId, int userId);
}
