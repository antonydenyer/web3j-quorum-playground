package net.consensys.quorum.examples.tests.net

import net.consensys.quorum.examples.tests.acceptance.contracts.VendingMachine
import org.junit.jupiter.api.Test
import org.web3j.crypto.Credentials
import org.web3j.protocol.http.HttpService
import org.web3j.quorum.JsonRpc2_0Quorum
import org.web3j.quorum.PrivacyFlag
import org.web3j.quorum.enclave.Tessera
import org.web3j.quorum.enclave.protocol.EnclaveService
import org.web3j.quorum.tx.QuorumTransactionManager
import org.web3j.utils.Async
import java.math.BigInteger

class RevertReasonTest {

    @Test
    fun testRevertReason() {
        val rpcUrl = "http://localhost:20000"
        val web3jService = HttpService(rpcUrl)
        val web3j = JsonRpc2_0Quorum(web3jService, 2000, Async.defaultExecutorService())


        val enclave = EnclaveService("http://127.0.0.1", 9081)

        val transactionManager = QuorumTransactionManager(
            web3j,
            Tessera(enclave , web3j),
            Credentials.create("b9a4bd1539c15bcc83fa9078fe89200b6e9e802ae992f13cd83c853f16e8bed4"),
            "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=",
            listOf("QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc="),
            PrivacyFlag.PARTY_PROTECTION
        )

        val contract = VendingMachine.deploy(
            web3j,
            transactionManager,
            BigInteger.valueOf(0),
            BigInteger.valueOf(4300000),
        ).send()




        var receipt = contract.buy(BigInteger.valueOf(5)).send()

    }
}
