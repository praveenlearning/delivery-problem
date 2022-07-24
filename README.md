## PACKAGE DELIVERY APPLICATION

It has two MainClasses
1. DeliveryCostEstimator
2. DeliveryTimeEstimator

### DeliveryCostEstimator 

Given packages input this class will calculate cost for all packages.
* input format
  * {base_cost} {packages_count}
  * {package_id} {weight} {distance} {offer_code}
  * ... {packages_count} times
* output format
  * {package_id} {discount} {cost}
  * ... {packages_count} times

### DeliveryTimeEstimator
* Given packages input this clas will calculate delivery time for all packages.
* input format
  * {base_cost} {packages_count}
  * {package_id} {weight} {distance} {offer_code}
  * ... {packages_count} times
  * {vehicles_count} {max_speed} {max_capacity}
* output format
  * {package_id} {discount} {cost} {delivery_time}
  * ... {packages_count} times