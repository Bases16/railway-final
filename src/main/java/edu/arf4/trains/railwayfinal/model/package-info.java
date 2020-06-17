@org.hibernate.annotations.GenericGenerator(
        name = "MY_ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "railway_sequence"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "76"
                )
        })
package edu.arf4.trains.railwayfinal.model;