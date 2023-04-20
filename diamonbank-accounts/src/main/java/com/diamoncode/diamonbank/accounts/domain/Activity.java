package com.diamoncode.diamonbank.accounts.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * A money transfer activity between {@link Account}s.
 */
@Value
@RequiredArgsConstructor
public class Activity {

	@Getter
	private ActivityId id;

	/**
	 * The account that owns this activity.
	 */
	@Getter
	@NonNull
	private final Long ownerAccountId;

	/**
	 * The debited account.
	 */
	@Getter
	@NonNull
	private final Long sourceAccountId;

	/**
	 * The credited account.
	 */
	@Getter
	@NonNull
	private final Long targetAccountId;

	/**
	 * The timestamp of the activity.
	 */
	@Getter
	@NonNull
	private final LocalDateTime timestamp;

	/**
	 * The money that was transferred between the accounts.
	 */
	@Getter
	@NonNull
	private final Money money;

	public Activity(
			@NonNull Long ownerAccountId,
			@NonNull Long sourceAccountId,
			@NonNull Long targetAccountId,
			@NonNull LocalDateTime timestamp,
			@NonNull Money money) {
		this.id = null;
		this.ownerAccountId = ownerAccountId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.timestamp = timestamp;
		this.money = money;
	}

	@Value
	public static class ActivityId {
		private final Long value;
	}

}