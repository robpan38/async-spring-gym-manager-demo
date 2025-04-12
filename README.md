# async-spring-gym-manager-demo

trainer

    firstName: string
    lastName: string
    specialization: list<enum>
trainee

    firstName: string
    lastName: string
    subscriptionType: enum
    expirationDate: date
class

    type: enum
    allowedSubscriptions: list<enum>
    trainer: trainer
    trainees: trainees
    length: int(hours)
    startDate: date
    endDate: date

# business cases:

    trainee:
        trainee joins gym after purchasing subscription -> trainee dto -> trainee entity -> stored in db
        trainee can update its info, or update/cancel subscription (cancel subscription -> delete from db)
        job is run every 5s to check which trainees subscriptions are still available
        trainee can attend class only if he has a valid subscription, (validation is done also at attend step to ensure correctness)

    trainer:
        trainer is hired -> trainer dto -> trainer entity -> stored in db
        trainer can have specializations, can only teach classes of type included in his specializations
        trainer info can be updated (earn specializations, or quit the gym)

    class:
        admin creates classes
        trainer can see only the classes which he is specialized into
        trainees can see all classes that he has valid subscription for
        lock mechanism: once a trainer enrolls himself as trainer for a class, that class is not available for other trainers
        delete classes from db after they start with scheduled job 

# security roles:
    
    add class - admin
    teach class - trainer with specialization
    attend classes & all trainee activities - trainee