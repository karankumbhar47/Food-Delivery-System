# Naming Conventions

## Main Types

| Name        | Example          | description                                                              |
| ----------- | ---------------- | ------------------------------------------------------------------------ |
| Camel Case  | numberOfDonuts   | starting with small letter, then all new word start with capital letter  |
| Pascal Case | NumberOfDonuts   | start all words with capital letter                                      |
| Sanke Case  | number_of_donuts | start all word with small letter, sepearate words with `underscore("_")` |
| Kebab Case  | number-of-donuts | start all word with small letter, sepearate words with `dash("-")`       |

## Naming Conventions of the Different Identifiers

| Identifier Type       | Naming rule used                                                            | Examples                                                                              |
| --------------------- | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| Class                 | Pascal Case                                                                 | MainActivity, DeviceSettingAdapter,<br> HomeFragment, UserModel, Employee             |
| Method(function)      | Camel Case                                                                  | onCreateView, load, setName                                                           |
| Variable              | Snake Case                                                                  | user_id, back_button, user_data                                                       |
| Instance of class     | Camel case (use recommended defuault names from android studio)             | firebaseFirestore , deviceModel, userModel                                            |
| Package(folder names) | all words start with capital letter,<br> and seperated by `underscore("_")` | Setting, Adapters, Setting_Fragments                                                  |
| Constants             | Snake Case with all capital letter                                          | LOGIN_STATUS, <br>KEY_USER_NAME, <br>DATA_USER_INFO, <br>PREF_DATA <br> STATUS_ACTIVE |

## Android spacific names(try to follow below naming convention)

### How to give ids in layout files

|         | Naming rule used                                                                    | Examples                                                 |
| ------- | ----------------------------------------------------------------------------------- | -------------------------------------------------------- |
| Views   | Camel(for view type) + Snake(use to combine both view type and name)                | device_model_textView<br> ,wifi_credential_cardView<br>> |
| Buttons | Camel(for view type button) + Snake(use to combine both view and name)              | back_button  <br> show_profile_button                    |
| Layout  | Camel(for layout type like linearLayout) + Snake(use to combine both view and name) | edit_profile_linearLayout                                |
| Others  | Camel(for view type ) + Snake(use to combine both view and name)                    | devices_recyclerView <br>, left_numberPicker             |

### How to give names to constant of databes

|                           | Naming rule used                             | Examples                                                                                                 |
| ------------------------- | -------------------------------------------- | -------------------------------------------------------------------------------------------------------- |
| Variable Table name       | Snake case with all caps                     | TABLE_CONSUMERS (define it in seperate file as follows) <br> String TABLE_CONSUMERS="Consumers"          |
| Variable Attributes names | Snake case with all caps                     | KEY_CONSUMER_NAME (define it in seperate file as follows) <br> String KEY_CONSUMER_NAME="Consumers-Name" |
| Actual Table name         | Kebab case with all Starting letters capital | Consumer, User-Information                                                                               |
| Actual Variable names     | Kebab case with all Starting letters capital | User-Name, Pickup-Time                                                                                   |

### Rules

- Give the same name as of id to variable while fetching it from layout  
    ex : -

    ```java
     CardView scanner_cardView = view.findViewById(R.id.scanner_cardView);
    ```

- While making new empty fragment, activity use android method for same


## 