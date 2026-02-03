import React from 'react';

export default function Input({
    label,
    name,
    type = 'text',
    value,
    onChange,
    placeholder,
    required = false,
    error,
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
            <input
                id={name}
                name={name}
                type={type}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                required={required}
                className={`input ${className}`}
                {...props}
            />
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
