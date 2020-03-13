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
                    creditor: $(producer('creditor'), consumer(anyNonBlankString())),
                    amount: $(anyNumber())
            ])
        }
        response {
            status CREATED()
            headers {
                contentType applicationJson()
            }
            body([

                            paymentsHubId   : $(anyUuid()),
                            status          : 'PENDING'

            ])
        }

    }
]



