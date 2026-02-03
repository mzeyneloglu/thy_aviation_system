import React from 'react';

export default function Select({
    label,
    name,
    value,
    onChange,
    options = [],
    required = false,
    error,
    placeholder = 'Select an option',
    className = '',
    ...props
}) {
    return (
        <div className="form-group">
            {label && (
                <label htmlFor={name} className="label">
                    {label} {required && <span style={{ color: 'var(--error-500)' }}>*</span>}
                </label>
            )}
            <select
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                required={required}
                className={`select ${className}`}
                {...props}
            >
                <option value="">{placeholder}</option>
                {options.map((option) => (
                    <option key={option.value} value={option.value}>
                        {option.label}
                    </option>
                ))}
            </select>
            {error && (
                <span style={{
                    color: 'var(--error-500)',
                    fontSize: '0.75rem',
                    marginTop: 'var(--spacing-xs)',
                    display: 'block'
                }}>
                    {error}
                </span>
            )}
        </div>
    );
}
