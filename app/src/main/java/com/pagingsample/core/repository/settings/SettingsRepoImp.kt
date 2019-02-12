package com.pagingsample.core.repository.settings

class SettingsRepoImp : SettingsRepo {
    override fun getClientSecret(): String {
        return "HZ0UNK0VXWOYBXPD1YF5X2CH103Z5YPARYSXJJKTY3UC4OH2"
    }

    override fun getClientId(): String {
        return "MR3HJG1MDW4FZFVHCG0223RLH4LFTHJZCJ15IOPR5402EIBP"
    }

    override fun networkPageSize(): Int {
        return 15
    }
}