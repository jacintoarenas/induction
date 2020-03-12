package rest

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        description 'should register payment'

        request {
            url '/miPago'
            method POST()
            headers {
                contentType applicationJson()
            }
            body([
                    debtor: $(producer('debtor'), consumer(anyNonBlankString())),
                    creditor: 'creditor',
                    amount: 1.1
            ])
        }
        response {
            status CREATED()
            headers {
                contentType applicationJsonUtf8()
            }
            body([

                            paymentsHubId   : $(anyUuid()),
                            status          : 'PENDING'

            ])
        }

    }
]



