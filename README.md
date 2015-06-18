# Hibernate JPA positional parameter bug when custom type used.

Teseted with hibernate 4.2.19.Final and hibernate 4.3.10.Final.

When there is a query that has conditions which have same Java type but custom hibernate types are different,
and thoue types' positional parameters have same number, there is a bug.


For example `User` entity has three Boolean type columns but their hibernate types are different.

```java
@Type(type = "true_false")
@Column(name = "employee", columnDefinition = "char(1)")
private Boolean employee;

@Type(type = "yes_no")
@Column(name = "male", columnDefinition = "char(1)")

@Column(name = "old")
private Boolean old;
```

In this situation, if you make a query like the following,

```java
final Query query = session.createQuery(
    "from User user where  employee = ?1 and  male = ?1 and  old = ?2");
query.setParameter("1", Boolean.TRUE);
query.setParameter("2", Boolean.FALSE);
```

Real generated sql and parameters are like the following,
```
select user0_.id as id1_0_, user0_.employee as employee2_0_, user0_.male as male3_0_,
user0_.name as name4_0_, user0_.old as old5_0_ from User user0_ 
where user0_.employee=? and user0_.male=? and user0_.old=?
```
```
{1: 'T', 2: 'T', 3: FALSE};
```

But male column's type is 'yes_no', so the second parameter must be 'Y' like the following,
```
{1: 'T', 2: 'Y', 3: FALSE};
```

If employee and male conditions are exchanged, like the following,
```
final Query query = session.createQuery(
    "from User user where  male = ?1 and employee = ?1 and old = ?2");
query.setParameter("1", Boolean.TRUE);
query.setParameter("2", Boolean.FALSE);
```
Real mapped parameters are like the following,
```
{1: 'Y', 2: 'Y', 3: FALSE};
```
But this must be like the following,
```
{1: 'Y', 2: 'T', 3: FALSE};
```