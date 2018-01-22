create table `user_role_map`(
  `user_id` int not null,
  `role_id` int not null,
  index `index_user_role_map_1`(`user_id` asc),
  index `index_product_user_role_2`(`role_id` asc),
  constraint `fk_user_role_map_1`
  foreign key (`user_id`)
  references `user`(`id`),
  constraint `fk_user_role_map_2`
  foreign key (`role_id`)
  references `role`(`id`))