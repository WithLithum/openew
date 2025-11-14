# Network protocol

## Play

### Clientbound

#### Change Client Flag

* **Identifier**: Resource `openew:change_client_flag`
* **State**: Play
* **Bound to**: Client

| Field Name | Field Type  | Notes                |
|------------|-------------|----------------------|
| Flag       | VarInt Enum | 0: None, 1: Hide HUD |
| Value      | Boolean     |                      | 
